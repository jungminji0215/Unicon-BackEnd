package sophist.common.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//테스트 중
// 스케쥴링으로 member, group data들을 statistics에 맞게 결과 도출 후 insert 
@Component
public class ArchivingMemStatisticsCron {
	@Scheduled(cron = "0 0/1 * * * ?")
	public void cronRun() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("### Cron Run 1 Minutes : " + simpleDateFormat.format(new Date()));
	}
}
