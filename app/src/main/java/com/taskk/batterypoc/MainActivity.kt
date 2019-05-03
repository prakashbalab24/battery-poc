package com.taskk.batterypoc

import android.Manifest
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.taskk.batterypoc.utils.FileLogUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(isStoragePermissionGranted()) {
            startJob()
            tv_file_log.text = FileLogUtils.readFromFile()
        }
        btn_file_write.setOnClickListener{
          FileLogUtils.appendLog("TEST FILE WRITER")
        }
    }

    private fun startJob() {
        val jobSed = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val compName = ComponentName(this@MainActivity,MyDemoJob::class.java)
        val builder = JobInfo.Builder(0,compName)
        builder.setPeriodic(900000)
        jobSed.schedule(builder.setPersisted(true).build())

    }

    fun isStoragePermissionGranted(): Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                return false
            }
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
          startJob()
        }
    }
}
