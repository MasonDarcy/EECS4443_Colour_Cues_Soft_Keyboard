import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Model model = new Model();
		
		model.readWords();
		model.precomputeAlphabeticIndices();
		model.precomputeProbabilities();
		model.serializeHashMap();
	
	}
	
}
