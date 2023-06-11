package com.example.androidtestbidv

import android.app.Activity
import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class SingleFlutterActivity : FlutterActivity() {
    private val CHANNEL = "demo"
    lateinit var methodChannel: MethodChannel;

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        );
        methodChannel.setMethodCallHandler { call, result ->
            if (call.method == "count") {
                Log.d("native",call.arguments.toString());
                if(call.arguments.toString().toInt() % 10 == 0) {
                    var i = Intent(this, MainActivity2 ::class.java)
                    i.putExtra("count", call.arguments.toString())
                    startActivityForResult(i, 111)
                }
                result.success("");
            } else {
                result.notImplemented()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111) {
            if (resultCode == Activity.RESULT_OK) {
                methodChannel.invokeMethod("authen", "success");
            } else {
                methodChannel.invokeMethod("authen", "fail");

            }
        }
    }
    //    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
//        super.configureFlutterEngine(flutterEngine)
//        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
//                call, result ->
//            // This method is invoked on the main thread.
//            // TODO
//        }
//    }
}