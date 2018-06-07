package conorodonnell.kotlinbug

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // This next line will trigger a crash caused by ClassNotFoundException on affected Kotlin versions
    val bugView = BugView(this)
  }
}
