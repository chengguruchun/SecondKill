package com.cheng.controller;

import com.cheng.dto.Exposer;
import com.cheng.dto.SeckillExecution;
import com.cheng.dto.SeckillResult;
import com.cheng.entity.Seckill;
import com.cheng.enums.SeckillStatEnum;
import com.cheng.exception.RepeatKillException;
import com.cheng.exception.SeckillCloseException;
import com.cheng.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author :cheng
 * @Description:
 * @Date: created in 14:27 2018/6/28
 * @Reference:
 */
@Controller
@RequestMapping("/seckill")//url: /模块/资源/{id}/细分
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill>   list =  seckillService.getSeckillList();
        model.addAttribute("list", list);

        return "list"; //WEB-INF/jsp/list.jsp  前缀和后缀已经在xml里面配置了
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {

            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill", seckill);
        return "detail";
    }

    //ajax -json
    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
    produces = {"application/json;charset=utf-8"})
    public SeckillResult<Exposer> /*TODo*/ exposer(@PathVariable("seckillId")Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
    produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
                                                    @PathVariable("md5")String md5,
                                                    @CookieValue(value = "killPhone", required = false)Long phone) {
        if (phone == null)
            return new SeckillResult<SeckillExecution>(false, "未注册");
        SeckillResult<SeckillExecution> result;
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            result = new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(false, execution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, execution);
        }
        return result;
    }

    //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }

    @RequestMapping("/testCookie")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId,
                                  @CookieValue("killPhone") String phone) {
        logger.info("phone:" + phone);
        logger.info("sessionId:" + sessionId);
        return "success";
    }

}