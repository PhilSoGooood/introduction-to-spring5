package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppConf2 {
	private final MemberDao memberDao;
	private final MemberPrinter memberPrinter;

	@Autowired
	public AppConf2(MemberDao memberDao, MemberPrinter memberPrinter) {
		this.memberDao = memberDao;
		this.memberPrinter = memberPrinter;
	}

	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao);
	}

	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService passwordService = new ChangePasswordService();
		passwordService.setMemberDao(memberDao);
		return passwordService;
	}

	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter);
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao);
		infoPrinter.setPrinter(memberPrinter);
		return infoPrinter;
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}


}
