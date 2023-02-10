package cn.bananice.basic.util;

import org.csource.fastdfs.*;

public class FastdfsUtil {

    //加载配置文件
    public static String CONF_FILENAME  = FastdfsUtil.class.getClassLoader() .getResource("fdfs_client.conf").getFile();


    /**
     * 上传文件
     * @param file ：byte数组格式的文件内容
     * @param extName ：文件扩展名
     * @return
     */
    public static  String upload(byte[] file,String extName) {

        try {
            //初始化配置
            ClientGlobal.init(CONF_FILENAME);
            //创建Tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //创建Storage 客户端
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            //设置而外的参数
            /**
            NameValuePair nvp [] = new NameValuePair[]{
                    new NameValuePair("width", "100"),
                    new NameValuePair("height", "100")
            }; **/

            //执行上传
            String fileIds[] = storageClient.upload_file(file,extName,null);

            String path =  "/"+fileIds[0]+"/"+fileIds[1];
            System.out.println("文件上传成功，path="+path);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    /**
     * 上传文件
     * @param extName
     * @return
     */
    public static  String upload(String path,String extName) {
 
        try { 
            ClientGlobal.init(CONF_FILENAME);
 
            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String fileIds[] = storageClient.upload_file(path, extName,null);
             
            System.out.println(fileIds.length); 
            return  "/"+fileIds[0]+"/"+fileIds[1];
 
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 下载文件
     * @param groupName
     * @param fileName
     * @return
     */
    public static byte[] download(String groupName,String fileName) {
        try {
 
            ClientGlobal.init(CONF_FILENAME);
 
            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
 
            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            return storageClient.download_file(groupName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        } 
    }


    /**
     * 删除文件
     * @param groupName
     * @param fileName
     */
    public static boolean delete(String groupName,String fileName){
        try { 
            ClientGlobal.init(CONF_FILENAME);
 
            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
 
            StorageClient storageClient = new StorageClient(trackerServer, 
                    storageServer); 
            int i = storageClient.delete_file(groupName,fileName);
            System.out.println( i==0 ? "删除成功" : "删除失败:"+i);
            return i == 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("删除异常,"+e.getMessage());
        } 
    }
}