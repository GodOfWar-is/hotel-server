package com.swpu.hotelserver.emp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchengbo
 * @since 2024-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间号：类似321
     */
    @TableField("room_num")
    private String roomNum;

    /**
     * 房间类型：单人间，双人间等
     */
    @TableField("type")
    private String type;

    /**
     * 价格
     */
    @TableField("price")
    private Integer price;

    /**
     * 房间描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 表示房间状态，1为可用，2为占用，-1为清扫
     */
    @TableField("status")
    private Integer status;


}
