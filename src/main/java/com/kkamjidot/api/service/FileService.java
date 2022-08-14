//package com.kkamjidot.api.service;
//
//import net.coobird.thumbnailator.Thumbnailator;
//import com.kkamjidot.api.exception.FileNotAllowedException;
//import com.kkamjidot.api.repository.FileRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class FileService {
//    private final FileRepository fileRepository;
//
//    @Value("${com.kkamjidot.api.upload.path}")
//    private String uploadPath;
//
//    public List<File> saveFiles(MultipartFile[] uploadFiles) {
//        for (MultipartFile uploadFile: uploadFiles) {
//
//            if(uploadFile.getContentType().startsWith("image") == false) {
//                throw new FileNotAllowedException("이미지 파일만 업로드 가능합니다.");
//            }
//
//            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
//            String originalName = uploadFile.getOriginalFilename();
//            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
//
//            //날짜 폴더 생성
//            String folderPath = makeFolder();
//
//            //UUID
//            String uuid = UUID.randomUUID().toString();
//
//            //저장할 파일 이름 중간에 "_"를 이용해서 구분
//            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid +"_" + fileName;
//            Path savePath = Paths.get(saveName);
//
//            try {
//                //원본 파일 저장
//                uploadFile.transferTo(savePath);
//
//                //섬네일 생성
//                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
//                        +"s_" + uuid +"_" + fileName;
//                //섬네일 파일 이름은 중간에 s_로 시작하도록
//                File thumbnailFile = new File(thumbnailSaveName);
//                //섬네일 생성
//                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100 );
//                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }//end for
//    }
//
//    private String makeFolder() {
//        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//
//        String folderPath =  str.replace("/", File.separator);
//
//        // make folder --------
//        File uploadPathFolder = new File(uploadPath, folderPath);
//
//        if (uploadPathFolder.exists() == false) {
//            uploadPathFolder.mkdirs();
//        }
//        return folderPath;
//    }
//}
