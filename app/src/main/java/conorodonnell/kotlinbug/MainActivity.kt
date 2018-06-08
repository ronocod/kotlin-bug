package conorodonnell.kotlinbug

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import rx.Observable
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import trikita.anvil.Anvil
import trikita.anvil.DSL.init
import trikita.anvil.appcompat.v7.AppCompatv7DSL.toolbar

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // This next line will trigger a crash caused by ClassNotFoundException on affected Kotlin versions
    val bugView = BugView(this)
  }
}

class BugView(context: Context) : FrameLayout(context, null), Anvil.Renderable {
  var subscription: Subscription? = null

  val subscriptions = CompositeSubscription()

  init {
    subscription = Observable.just(1)
      .subscribe({
        Anvil.render(this)
      }, Throwable::printStackTrace)
  }

  override fun view() {
    toolbar {
      init {
        Anvil.currentView<Toolbar>().apply {
          menu.findItem(1234)?.apply {
            setOnMenuItemClickListener a@{
              AlertDialog.Builder(context)
                .setPositiveButton("ok", { _, _ ->

                  // When line A is uncommented here, the app will crash on affected versions
                  // When line B is uncommented instead, the app will not crash

                  /* A */ subscriptions.add(Observable.just(1)

//                  /* B */ (Observable.just(1)

                  .subscribe(
                    { },
                    Throwable::printStackTrace
                  ))
                })
                .show()
              return@a true
            }
          }
        }
      }
    }
  }
}
