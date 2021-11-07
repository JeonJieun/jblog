package com.douzone.jblog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	private static String SAVE_PATH = "/logo-jblog";
	private static String URL_BASE = "/blog/logo";

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		BlogVo blogVo = new BlogVo();
		CategoryVo categoryVo = new CategoryVo();

		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getId());
		blogVo.setLogo(restoreImage("spring-logo.jpg"));

		blogRepository.insert(blogVo);
		
		categoryVo.setName("미분류");
		categoryVo.setBlogId(userVo.getId());
		
		categoryRepository.insert(categoryVo);

	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public String restoreImage(String saveFilename) throws FileUploadException {

		File uploadDirectory = new File(SAVE_PATH);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdir();
		}

		// 원본 파일경로
		String oriFilePath = "/douzone2021/eclipse-workspace/jblog/jblog03/src/main/webapp/assets/images/" + saveFilename;
		// 복사될 파일경로
		String copyFilePath = SAVE_PATH + "/" + saveFilename;

		// 파일객체생성
		File oriFile = new File(oriFilePath);
		// 복사파일객체생성
		File copyFile = new File(copyFilePath);

		try {
			if (!copyFile.exists()) {	
				FileInputStream fis = new FileInputStream(oriFile); // 읽을파일
				FileOutputStream fos = new FileOutputStream(copyFile); // 복사할파일

				int fileByte = 0;
				// fis.read()가 -1 이면 파일을 다 읽은것
				while ((fileByte = fis.read()) != -1) {
					fos.write(fileByte);
				}
				// 자원사용종료
				fis.close();
				fos.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return URL_BASE + "/" + saveFilename;
	}

}