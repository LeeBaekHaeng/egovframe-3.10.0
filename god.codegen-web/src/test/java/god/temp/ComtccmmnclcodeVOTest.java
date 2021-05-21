package god.temp;

import java.time.LocalDateTime;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComtccmmnclcodeVOTest {

	@Test
	public void test() {
		ComtccmmnclcodeVO vo = new ComtccmmnclcodeVO();

		vo.setClCode("test 분류코드");
		vo.setClCodeNm("test 분류코드명");
		vo.setClCodeDc("test 분류코드설명");
		vo.setUseAt("test 사용여부");
		vo.setFrstRegistPnttm(LocalDateTime.now());
		vo.setFrstRegisterId("test 최초등록자ID");
		vo.setLastUpdtPnttm(LocalDateTime.now());
		vo.setLastUpdusrId("test 최종수정자ID");

		log.debug("clCode={}", vo.getClCode());
		log.debug("clCodeNm={}", vo.getClCodeNm());
		log.debug("clCodeDc={}", vo.getClCodeDc());
		log.debug("useAt={}", vo.getUseAt());
		log.debug("frstRegistPnttm={}", vo.getFrstRegistPnttm());
		log.debug("frstRegisterId={}", vo.getFrstRegisterId());
		log.debug("lastUpdtPnttm={}", vo.getLastUpdtPnttm());
		log.debug("lastUpdusrId={}", vo.getLastUpdusrId());
	}

}
