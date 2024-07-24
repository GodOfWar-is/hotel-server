package com.swpu.hotelserver.emp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hotel
 * @since 2024-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("form")
public class Form implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("room_num")
    private String roomNum;

    /**
     * 入住时间
     */

    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 退房时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 入住人员1
     */
    @TableField("client1_name")
    private String client1Name;

    /**
     * 入住人员2
     */
    @TableField("client2_name")
    private String client2Name;

    /**
     * 入住人员3
     */
    @TableField("client3_name")
    private String client3Name;

    /**
     * 入住人员4
     */
    @TableField("client4_name")
    private String client4Name;


}
