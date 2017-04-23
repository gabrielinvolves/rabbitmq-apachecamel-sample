package test.message;

/**
 * 
 * @author Gabriel
 *
 */
public class SimpleImmutableMessage {

	private long timemillis;
	private String content;

	public SimpleImmutableMessage(long timemillis, String content) {
		super();
		this.timemillis = timemillis;
		this.content = content;
	}

	public long getTimemillis() {
		return timemillis;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return timemillis + " - " + content;
	}

}
