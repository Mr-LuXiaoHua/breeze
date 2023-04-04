package com.breeze.common.bo;

import lombok.Data;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/27
 */
@Data
public class ServerInfo {

    private Memory memory;

    private List<Disk> diskList;



    /**
     * 内存
     */
    @Data
    public static class Memory {

        /**
         * 总物理内存
         */
        private String totalPhysicalMemorySize;
        /**
         * 空闲物理内存
         */
        private String freePhysicalMemorySize;

        /**
         * Java虚拟机最大内存
         */
        private String jvmMaxMemory;

        /**
         * Java虚拟机空闲内存
         */
        private String jvmFreeMemory;

    }

    /**
     * 磁盘
     */
    @Data
    public static class Disk {

        /**
         * 盘符
         */
        private String canonicalPath;

        /**
         * 总容量
         */
        private String totalSpace;

        /**
         * 剩余容量
         */
        private String freeSpace;
    }

}
