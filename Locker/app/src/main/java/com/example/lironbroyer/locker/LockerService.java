package com.example.lironbroyer.locker;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LockerService extends IntentService {
    private ApplicationInfo whatsappString = null;
    private Intent mServiceIntent = null;

    public LockerService(String name) {
        super(name);
    }

    public LockerService() {
        super("Liron");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        List<String> backgroundApps = getAllBackgroundApps();
        //String foregroundApp = getForegroundApp();

        if (whatsappString.packageName.contains("whatsapp")) {
            lockApp();
        }
    }

    private List<String> getAllBackgroundApps() {
        PackageManager packageManager = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        List<String> apps = new ArrayList<>();
        List<PackageInfo> packs = packageManager.getInstalledPackages(0);
        for(int i=0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo a = p.applicationInfo;
            // skip system apps if they shall not be included
            if((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }
            apps.add(p.packageName);

            if (a.packageName.contains("whatsapp") || a.processName.contains("whatsapp")){
                whatsappString = a;
            }
        }

        return apps;
    }

    private String getForegroundApp() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
//        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
//        String activityOnTop = ar.topActivity.getClassName();
         String foregroundProccessName = mActivityManager.getRunningAppProcesses().get(0).processName;
        return foregroundProccessName;
//        return activityOnTop;
    }

    private void lockApp() {
        mServiceIntent = new Intent(this, OrActivity.class);
        mServiceIntent.setData(Uri.parse("http://localhost:123/Liron"));
        this.startActivity(mServiceIntent);
    }
}