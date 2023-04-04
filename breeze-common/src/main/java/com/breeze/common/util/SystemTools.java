package com.breeze.common.util;


import com.breeze.common.bo.ServerInfo;
import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 一枕清风
 * @date 2023/3/27
 */
public class SystemTools {

    public static ServerInfo getSystemInfo() {

        ServerInfo.Memory memory = new ServerInfo.Memory();



        OperatingSystemMXBean mxBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

        // 获取内存总容量
        long totalPhysicalMemorySize = mxBean.getTotalPhysicalMemorySize();
        // 获取可用内存容量
        long freePhysicalMemorySize = mxBean.getFreePhysicalMemorySize();

        memory.setTotalPhysicalMemorySize(transformation(totalPhysicalMemorySize));
        memory.setFreePhysicalMemorySize(transformation(freePhysicalMemorySize));

        Runtime runtime = Runtime.getRuntime();
        // java虚拟机使用内存总量
        long jvmTotalMemory = runtime.totalMemory();
        // java虚拟机试图使用的最大内存空间
        long jvmMaxMemory = runtime.totalMemory();
        // java虚拟机中空闲内存总量
        long jvmFreeMemory = runtime.freeMemory();

        memory.setJvmMaxMemory(transformation(jvmMaxMemory));
        memory.setJvmFreeMemory(transformation(jvmFreeMemory));


        List<ServerInfo.Disk> diskList = new ArrayList<>();
        File[] disks = File.listRoots();
        for (File file : disks) {
            try {
                // 盘符
                String canonicalPath = file.getCanonicalPath();
                // 总容量
                long totalSpace = file.getTotalSpace();
                // 剩余容量
                long freeSpace = file.getFreeSpace();

                ServerInfo.Disk disk = new ServerInfo.Disk();
                disk.setCanonicalPath(canonicalPath);
                disk.setTotalSpace(transformation(totalSpace));
                disk.setFreeSpace(transformation(freeSpace));

                diskList.add(disk);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setMemory(memory);
        serverInfo.setDiskList(diskList);

        return serverInfo;
    }





    private static String transformation(long size) {
        DecimalFormat df = new DecimalFormat("#0.000000");
        float result = (float)size / 1024 / 1024 / 1024;
        return df.format(result) + " GB";
    }


}
