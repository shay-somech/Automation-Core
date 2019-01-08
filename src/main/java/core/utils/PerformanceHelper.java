package core.utils;

import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.List;

public class PerformanceHelper {

    /**
     *
     * @param driver only AndroidDriver is compatible at this moment
     * @return HashMap of data {key, value}
     * @keys [eglPss, glPrivateDirty, totalPrivateDirty, dalvikPss, nativePrivateDirty, glPss, totalPss, nativeHeapAllocatedSize, dalvikPrivateDirty, nativeHeapSize, eglPrivateDirty, nativePss]
     * @value
     * @throws Exception
     */
    public HashMap<String, Integer> getMemoryInfo(AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(AppParams.getAndroidAppPackage(), "memoryinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    /**
     *
     * @param driver only AndroidDriver is compatible at this moment
     * @return HashMap of data {key, value}
     * @key
     * @value
     * @throws Exception
     */
    public HashMap<String, Integer> getCPUInfo(AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(AppParams.getAndroidAppPackage(), "cpuinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    /**
     *
     * @param driver only AndroidDriver is compatible at this moment
     * @return HashMap of data {key, value}
     * @key [power]
     * @value Battery level as int
     * @throws Exception
     */
    public HashMap<String, Integer> getBatteryInfo(AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(AppParams.getAndroidAppPackage(), "batteryinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    /**
     *
     * @param driver only AndroidDriver is compatible at this moment
     * @return HashMap of data {key, value}
     * @keys [rb, st, op, bucketDuration, activeTime, tp, rp, tb]
     * @value
     * @throws Exception
     */
    public HashMap<String, Integer> getNetworkInfo(AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(AppParams.getAndroidAppPackage(), "networkinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

}
