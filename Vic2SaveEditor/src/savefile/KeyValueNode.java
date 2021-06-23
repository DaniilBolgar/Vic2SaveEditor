package savefile;

public class KeyValueNode extends Node {
	String key;
	String value;
	Node node;
	
	public KeyValueNode(String k, String v) {
		this.key = k;
		this.value = v;
	}
	public KeyValueNode(String k, Node n) {
		this.key = k;
		this.node = n;
	}
}
