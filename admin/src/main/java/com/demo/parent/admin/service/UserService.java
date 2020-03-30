package com.demo.parent.admin.service;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.vo.UserAddVO;
import com.demo.parent.admin.vo.UserEditVO;
import com.demo.parent.admin.vo.UserVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;

/**
 * projectName demo
 * interfaceName LoginService
 *
 * @author yzh
 * @date 2020/3/18 4:50 下午
 */
public interface UserService {

    /**
     * @author  yzh
     * @date    2020/3/19
     */
    UserDTO queryUserByAccount(String account);

    /**
     * 登录
     * @param user 用户信息
     * @return 返回信息
     * @author  yzh
     * @date    2020/3/25
     */
    Response<UserVO> login(UserVO user);

    /**
     * 分页展示信息
     * @param  pageNum 页号
     * @param  pageSize 页面尺寸
     * @return  统一返回
     * @author  yzh
     * @date    2020/3/25
     */
    //Response<PageInfo> queryAllByPage(Integer pageNum,Integer pageSize);

    PageInfo<UserDTO> queryAllByPage(Integer pageNum,Integer pageSize);

    /**
     * 根据id查询用户信息
     * @param   id 主键
     * @return  UserEditVO 编辑窗口展示信息
     * @author  yzh
     * @date    2020/3/26
     */
    Response<UserEditVO> queryUserById(Integer id);

    /**
     * 更新用户信息
     * @param vo
     * @return  Response
     * @author  yzh
     * @date    2020/3/26
     */
    Response updateUser(UserEditVO vo);

    /**
     * 新增用户
     * @param  vo
     * @return Response
     * @author  yzh
     * @date    2020/3/29
     */
    Response addUser(UserAddVO vo);
}
