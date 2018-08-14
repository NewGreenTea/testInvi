package com.confClass;

public class DynamicDataSourceContextholder {
    private static final ThreadLocal<String> currentDatasource=new ThreadLocal<String>();

    /**
     * 清除当前数据源
     */
    public static void clear() {
        currentDatasource.remove();
    }

    /**
     * 获取当前使用的数据源
     *
     * @return 当前使用数据源的ID
     */
    public static String get() {
        return (String) currentDatasource.get();
    }

    /**
     * 设置当前使用的数据源
     *
     * 需要设置的数据源（名）
     */
    public static void setCurrentDatasource(String datasourceType){
        currentDatasource.set(datasourceType);
    }
}
