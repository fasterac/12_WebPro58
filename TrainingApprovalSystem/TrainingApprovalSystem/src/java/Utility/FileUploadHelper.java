package utility;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadHelper {

    private static final String TEMP_PATH = "temp";
    private static final String COURSE_PATH = "course";
    private static final String REPORT_PATH = "report";

    private String appPath;
    private String savePath;

    private HttpServletRequest request;

    public FileUploadHelper(HttpServletRequest request) {
        this.request = request;

        appPath = request.getServletContext().getRealPath("");
        savePath = appPath + "upload/";

        File tempPath = new File(savePath + TEMP_PATH);
        if(!tempPath.exists()) {
            tempPath.mkdirs();
        }
        tempPath = new File(savePath + COURSE_PATH);
        if(!tempPath.exists()) {
            tempPath.mkdirs();
        }
        tempPath = new File(savePath + REPORT_PATH);
        if(!tempPath.exists()) {
            tempPath.mkdirs();
        }
    }

    public File uploadToTemp(Part filePart) throws IOException {
        String fileName = filePart.getSubmittedFileName();

        String[] fileNameDotSplit = fileName.split("\\.");
        String fileExtension = fileNameDotSplit[fileNameDotSplit.length - 1];
        String fileRealName = "";
        for(int i = 0; i < fileNameDotSplit.length - 1; i++) {
            fileRealName += fileNameDotSplit[i];
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert md != null;
        md.update(fileRealName.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }

        String newFileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_" + sb.toString() + "." + fileExtension;

        filePart.write(savePath + TEMP_PATH + File.separator + newFileName);
        return new File(savePath + TEMP_PATH + File.separator + newFileName);
    }

    public boolean moveFileToCourseFile(File file) throws IOException {
        return file.renameTo(new File(savePath + COURSE_PATH + File.separator + file.getName()));
    }

    public boolean moveFileToReportFile(File file) throws IOException {
        return file.renameTo(new File(savePath + REPORT_PATH + File.separator + file.getName()));
    }

    public File getTempFile(String filename) {
        return new File(savePath + TEMP_PATH + File.separator + filename);
    }

    public File getCourseFile(String filename) {
        return new File(savePath + COURSE_PATH + File.separator + filename);
    }

    public String getCourseFileURL(String filename) {
        return RouteHelper.generateURL(request, "upload/course/" + filename);
    }

    public File getReportFile(String filename) {
        return new File(savePath + REPORT_PATH + File.separator + filename);
    }

    public boolean removeFile(File file) throws IOException {
        return file.delete();
    }

}
