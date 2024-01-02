package com.tiny.world.domain;

import lombok.Data;

@Data
public class VersionedTrade {

	private String tradeId;

	private Integer tradeVersion;

}