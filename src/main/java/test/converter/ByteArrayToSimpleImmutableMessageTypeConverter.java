package test.converter;

import org.apache.camel.Exchange;
import org.apache.camel.support.TypeConverterSupport;

import test.message.SimpleImmutableMessage;

/**
 * 
 * @author Gabriel
 *
 */
public class ByteArrayToSimpleImmutableMessageTypeConverter extends
		TypeConverterSupport {

	@SuppressWarnings("unchecked")
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {
		String strValue = new String((byte[]) value);
		String[] string = strValue.split(";");
		SimpleImmutableMessage result = new SimpleImmutableMessage(
				Long.parseLong(string[0]), string[1]);

		return (T) result;
	}

}
