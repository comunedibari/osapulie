package it.osapulie.pratiche.web.portlet.varie;

import java.io.File;
import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MultipartFileToFileConverter implements
		Converter<CommonsMultipartFile, File> {

	@Override
	public File convert(CommonsMultipartFile source) {
		File file = new File(source.getOriginalFilename());
		try {
			FileCopyUtils.copy(source.getBytes(), file);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
