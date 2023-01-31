package br.faccat.projeto.projetofaccat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {

    private final String PATH="/opt/config/files/";

    public void saveFile(MultipartFile file, String folderName){
        File folder=this.createFolder(PATH+folderName);

        File newFile=new File(folder.getAbsolutePath()+"/"+file.getOriginalFilename());
        try(FileOutputStream out=new FileOutputStream(newFile)){
            InputStream data=file.getInputStream();
            byte[] buffer=new byte[1024];
            int length=data.read(buffer);
            while(length !=-1){
                out.write(buffer,0, length);
                length=data.read(buffer);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public File createFolder(String path){
        try {
            Runtime.getRuntime().exec("mkdir -p "+path).waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file=new File(path);
        return file;
    }
}
