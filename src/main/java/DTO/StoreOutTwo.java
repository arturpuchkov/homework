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
public class StoreOutTwo{
	private int code;
	private String type;
	private String message;
}