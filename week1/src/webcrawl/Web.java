package webcrawl;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
public class Web {
 
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        //String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";
        //ArrayList<String> list = getSource(url);
    //  for(String s : list)
        //  System.out.println(s);
 
        //���丮�� �̸� �������ƾ�
        String saveDir ="/Users/HANSOL/Documents/WebCrawlerDownload/";
        //�ƹ� ��ũ
        String downloadUrl = "http://www.naver.com";
        String typeRgx = "(jpg)";
 
        Web.getTypedFileDown(downloadUrl, saveDir, typeRgx);
 
    }
 
    public static String getSource(String url) throws MalformedURLException, IOException {
    	//  ArrayList<String> output = new ArrayList<>();
        String output="";
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(),"utf-8"));
        String line;
        while ((line = br.readLine()) != null) {
            //output.add(line);
            output += line;
        }
        return output;
    }
 
    public static ArrayList<String> getTypedFile(String text, String typeRegex) {
        String regex = "\"(http://|https://)[^<>\"]+[.]" + typeRegex + "\"";
        /*
        1. "(�ο��ȣ)�� �����ϰ�, http://�� "https://�� �� ������ ���´�.
		2. ���ڿ� �߰��� < �̳� > �Ǵ� "(�ο��ȣ)�� ���� �ʴ´�.
		3. �׸��� .typeRegex�� ���� �� "�� ������.
         */
        
        Matcher m = Pattern.compile(regex).matcher(text);
        ArrayList<String> output = new ArrayList<>();
        while (m.find()) {
            output.add(m.group().replace("\"", ""));
        }
        return output;
    }
 
    public static void FileDownload(String address, String saveDir){
        try{
            URL u = new URL(address);
            FileOutputStream fos = new FileOutputStream(saveDir);
            InputStream is = u.openStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();}catch(IOException e){
                e.printStackTrace();
                System.exit(0);
            }
    }
 
    public static String getWebFileName(String filePath) {
        String[] parts = filePath.split("[/]");
        return parts[parts.length - 1];
    }
 
    public static void getTypedFileDown(String url, String saveDir, String typeRegex) throws IOException {
        String source = getSource(url); //source ��������
        ArrayList<String> urls = getTypedFile(source, typeRegex); //���� ���ϴ� ������ �ּҰ� ã��
        System.out.println("�ٿ�ε带 �����մϴ�.");
        int size = urls.size();
        int k = 1;
        for (String i : urls) {
            System.out.println();
            System.out.println("//--------------------------------------");
            System.out.println("//   �ٿ�ε� ��... : " + i);
            String save = saveDir + "\\" + getWebFileName(i);
            System.out.println("//    �ٿ�ε� ��ġ : " + save);
            FileDownload(i, save); //���� �ٿ�ε�
            System.out.println("//   �ٿ�ε� ����� : " + (k * 100 / size) + "%");
            System.out.println("//--------------------------------------");
            System.out.println();
            k++;
        }
        System.out.println("�� " + urls.size() + "���� ������ �ٿ�ε��Ͽ����ϴ�.");
    }
 
}
 