package simpleJGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class MultiLabel extends Label {
	private final ArrayList<String> lines;

	public MultiLabel(List<String> lines) {
		this.lines = new ArrayList<>(lines);
		setText();
	}

	public MultiLabel() {
		this.lines = new ArrayList<>();
		setText();
	}

	public void addLines(int index, String... lines) {
		this.lines.addAll(index, List.of(lines));
		setText();
	}

	public void addLines(String... lines) {
		this.lines.addAll(Arrays.asList(lines));
		setText();
	}

	public void removeLines(int startIndex, int endIndex) {
		if (endIndex > startIndex)
			this.lines.subList(startIndex, endIndex).clear();
		setText();
	}

	public void removeLine(int index) {
		this.lines.remove(index);
		setText();
	}

	private void setText() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < lines.size() - 1; i++)
			builder.append(String.format("%s%n", lines.get(i)));

		builder.append(lines.getLast());
		setText(builder.toString());
	}
}
