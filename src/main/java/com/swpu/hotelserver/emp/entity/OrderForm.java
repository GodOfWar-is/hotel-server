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
 * @author hotel
 * @since 2024-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order")
public class OrderForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间id
     */
    @TableField("room_id")
    private Integer roomId;

    /**
     * 客户id
     */
    @TableField("client_id")
    private Integer clientId;

    /**
     * 订单编号
     */
    @TableField("order_num")
    private String orderNum;


}
