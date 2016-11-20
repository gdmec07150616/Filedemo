package cn.gdmec.lyc07150616.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhonedirecptry;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDiertory;
    private File fDataStorage;
    private File fDownloadCacheDirectory;
    private File fRootDirectory;
    private String name,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.result);
        fPhonedirecptry=this.getFilesDir();
        fExternalStoragePublicDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDiertory=Environment.getExternalStorageDirectory();
        fDataStorage=Environment.getDataDirectory();
        fDownloadCacheDirectory=Environment.getDownloadCacheDirectory();
        fRootDirectory=Environment.getRootDirectory();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)){
            Button btn=(Button) findViewById(R.id.externalStorageDiertory);
            btn.setEnabled(false);
        }
    }
    public void phoneDirectory(View v){
        path=fPhonedirecptry.getPath();
        try {
            FileOutputStream fos=openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("Hello".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListFiles(path);
    }
public void externalStoragePublicDirectory(View v){
    path=fExternalStoragePublicDirectory.getAbsolutePath();
    ListFiles(path);
}
    public void externalStorageDiertory(View v){
        path=fExternalStorageDiertory.getAbsolutePath();
        ListFiles(path);
    }
    public void dataStorage(View v){
        path=fDataStorage.getAbsolutePath();
        ListFiles(path);
    }
    public void downloadCacheDirectory(View v){
        path=fDownloadCacheDirectory.getAbsolutePath();
        ListFiles(path);
    }
    public void rootDirectory(View v){
        path=fRootDirectory.getAbsolutePath();
        ListFiles(path);
    }

    private boolean ListFiles(String path) {
        name="路径:"+path+"\n文件清单:\n";
        File file=new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f:file.listFiles()){
                path=f.getAbsolutePath();
                name=name+f.getName()+"\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}
