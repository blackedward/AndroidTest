package com.test.xiaoyang.xitest;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView lv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> appnames = getAppNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appnames);


        tv = (TextView) findViewById(R.id.text_title);
        lv = (ListView) findViewById(R.id.list_appname);
        lv.setAdapter(adapter);
    }

    private ArrayList<String> getAppNames() {
        ArrayList<String> pNames = new ArrayList<String>();
        ArrayList<AppInfo> appList = new ArrayList<AppInfo>(); //用来存储获取的应用信息数据　　　　　
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpInfo = new AppInfo();
            tmpInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            tmpInfo.packageName = packageInfo.packageName;
            pNames.add(tmpInfo.packageName);
            tmpInfo.versionName = packageInfo.versionName;
            tmpInfo.versionCode = packageInfo.versionCode;
            tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
            appList.add(tmpInfo);
        }

        return pNames;
    }



}
