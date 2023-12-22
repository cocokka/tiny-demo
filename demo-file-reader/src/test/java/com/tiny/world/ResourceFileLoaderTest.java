package com.tiny.world;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tiny.world.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootTest
class ResourceFileLoaderTest {

	@Autowired
	private JsonMapper jsonMapper;

	@Test
	void test() throws JsonProcessingException {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建ClassPathResource对象，指定resource路径
			Resource resource = new ClassPathResource("/input/user.json");

			// 打开文件输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

			// 读取文件内容
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}

			// 关闭文件输入流
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		User user = jsonMapper.readValue(sb.toString(), User.class);
		System.out.println("user = " + user);
		Assertions.assertThat(user).isNotNull();
	}

}
