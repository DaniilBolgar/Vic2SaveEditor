package savefile;

import java.util.ArrayList;

public class Parser {
	final int NORMAL_STATE = 0;
	final int SEARCH_FOR_OPENING_BRACE = 1;
	final int SEARCH_FOR_CLOSING_BRACE = 2;
	
	int state;
	ContainerNode root;
	
	public void parse(SaveFile sf) {
		state = NORMAL_STATE;
		root = new ContainerNode();
		ArrayList<String> strs = sf.getTranscribedSaveFile();
		parse(strs, 0, root);
	}
	
	public int parse(ArrayList<String> strs, int start_index, ContainerNode parent) {
		int i;
		for (i = start_index; i < strs.size(); ++i) {
			String s = strs.get(i).trim();
			if (state == NORMAL_STATE) {
				// check if we may finish parsing
				if (s.equals("}")) {
					break;
				}
				
				// Check if value array case
				if (s.endsWith("}")) {
					String ss = s.substring(0, s.length() - 1);
					String[] varr = s.split(" ");
					ValuesArrayNode n = new ValuesArrayNode(varr);
					parent.add(n);
				}
				
				if (!s.contains("=")) {
					// A single value
					String varr[] = { s };
					ValuesArrayNode n = new ValuesArrayNode(varr);
					parent.add(n);
					continue;
				}
				String[] kvArr = s.split("=");
				assert kvArr.length <= 2;
			
				if (kvArr.length == 2) {
					// key - value
					KeyValueNode n = new KeyValueNode(kvArr[0], kvArr[1]);
					parent.add(n);
				} else if (kvArr.length == 1) {
					// nested node
					state = SEARCH_FOR_OPENING_BRACE;
					
					// Create new container for nested nodes
					ContainerNode nestedContainer = new ContainerNode(parent);
					// save it as key-value in the current container
					KeyValueNode n = new KeyValueNode(kvArr[0], nestedContainer);
					parent.add(n);
					// parse into new container
					i = parse(strs, i + 1, nestedContainer);
					
				}
			} else if (state == SEARCH_FOR_OPENING_BRACE) {
				if (s.equals("{")) {
					state = NORMAL_STATE;
				}
			}
		}
		return i;
	}
}
