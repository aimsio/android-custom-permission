package io.github.aimsio.custompermission

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val permission_script_server = "com.aimsio.android.CONNECT_TO_SCRIPT_SERVER"
    val permission_form_data = "com.aimsio.android.READ_FORM_DATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_script_server.setOnClickListener {
            if (checkCallingOrSelfPermission(permission_script_server) == PackageManager.PERMISSION_GRANTED) {
                showMessage("script server permission: granted")
            } else {
                showMessage("script server permission : denied")
            }
        }

        button_form_data.setOnClickListener {
            if (checkCallingOrSelfPermission(permission_form_data) == PackageManager.PERMISSION_GRANTED) {
                showMessage("form data permission: granted")
            } else {
                showMessage("form data permission : denied")

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission_form_data),
                    1
                );
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMessage("form data permission: granted")
            } else {
                showMessage("permission denied to read form data!")
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
