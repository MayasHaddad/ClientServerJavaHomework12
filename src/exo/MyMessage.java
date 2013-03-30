package exo;

public class MyMessage implements Message {

	private String source;
	private String content;

	@Override
	public String getSource() {

		return this.source;
	}

	@Override
	public String getContent() {

		return this.content;
	}

	public MyMessage(String source, String content){

		this.source=source;
		this.content=content;

	}
}
