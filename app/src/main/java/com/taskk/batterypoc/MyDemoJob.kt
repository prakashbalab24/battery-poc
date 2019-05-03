package com.taskk.batterypoc

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.taskk.batterypoc.utils.FileLogUtils
import com.taskk.batterypoc.utils.GeneralUtils
import com.taskk.batterypoc.utils.NotificationUtil

/*
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MM.:  .:'   `:::  .:`MMMMMMMMMMM|`MMM'|MMMMMMMMMMM':  .:'   `:::  .:'.MM
MMMM.     :          `MMMMMMMMMM  :*'  MMMMMMMMMM'        :        .MMMM
MMMMM.    ::    .     `MMMMMMMM'  ::   `MMMMMMMM'   .     ::   .  .MMMMM
MMMMMM. :   :: ::'  :   :: ::'  :   :: ::'      :: ::'  :   :: ::.MMMMMM
MMMMMMM    ;::         ;::         ;::         ;::         ;::   MMMMMMM
MMMMMMM .:'   `:::  .:'   `:::  .:'   `:::  .:'   `:::  .:'   `::MMMMMMM
MMMMMM'     :           :           :           :           :    `MMMMMM
MMMMM'______::____      ::    .     ::    .     ::     ___._::____`MMMMM
MMMMMMMMMMMMMMMMMMM`---._ :: ::'  :   :: ::'  _.--::MMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMM::.         ::  .--MMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM-.     ;::-MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM. .:' .prakashbala MMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.   .MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\ /MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
*/

class MyDemoJob : JobService(){
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.i("MyDemoJob","Running...")
        FileLogUtils.appendLog(GeneralUtils.getReadableTime() +" -- Battery Percentage: "+ GeneralUtils.getBatteryPercentage(applicationContext))
        NotificationUtil.addNotification(applicationContext)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
       return false
    }

}