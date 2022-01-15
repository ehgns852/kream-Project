package com.nklcb.kream.entity.item;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UploadFile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "upload_file_id")
    private Long id;

    //고객이 업로드한 파일명
    private String uploadFileName;

    //서버 내부에서 관리하는 파일명
    private String storeFileName;

    private String filePath;


    public UploadFile(String uploadFileName, String storeFileName,String filePath) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.filePath = filePath;
    }

    public void updateUploadFile(String uploadFileName, String storeFileName,String filePath) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.filePath = filePath;

    }


}
