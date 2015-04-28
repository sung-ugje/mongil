/**
 * 몬스터 Controller
 */
package com.openapi.controller.api.monster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openapi.controller.BaseOpenApiController;
import com.openapi.domain.monster.Monster;
import com.openapi.service.monster.MonsterService;

@Controller
@RequestMapping("/api")
public class MonsterController extends BaseOpenApiController {

	@Autowired
	private MonsterService MonsterService;


	/**
	 * 몬스터 목록 조회
	 * 
	 * @param brndNo
	 *            : 관리 브랜드 번호
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mon/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Monster> getMonsterList(
			@RequestParam(value="isPaging", required=false, defaultValue="true") boolean isPaging,
			@RequestParam(value="pg", required=false, defaultValue="1") int pg,
			@RequestParam(value="ps", required=false, defaultValue="10") int ps
			)
			throws Exception {

		Monster mon = new Monster();
		mon.setPg(pg);
		mon.setPs(ps);
		mon.setIsPaging(isPaging);
		return MonsterService.getMonsterList(mon);
	}

}
