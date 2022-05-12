package DTO;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreOutThree{
	private int totvs;
	private int sold;
	private int available11;
	private int test;
	private int string;
	private int pending;
	private int available;
	private int free;
	private int connector;
	private int notAvailable;
	private int status16636;
	private int status;
}