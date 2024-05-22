package it.jac.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class DataTableRequest {

	private int draw;
	private int length;
	private List<DTOrder> order = new ArrayList<>();
	private DTSearch search;
	private int start;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<DTOrder> getOrder() {
		return order;
	}

	public void setOrder(List<DTOrder> order) {
		this.order = order;
	}

	public DTSearch getSearch() {
		return search;
	}

	public void setSearch(DTSearch search) {
		this.search = search;
	}

	public static class DTOrder {

		private int col;
		private String name;
		private String dir;

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "DTOrder [col=" + col + ", name=" + name + ", dir=" + dir + "]";
		}

	}

	public static class DTSearch {

		private String value;
		private boolean regex;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isRegex() {
			return regex;
		}

		public void setRegex(boolean regex) {
			this.regex = regex;
		}

		@Override
		public String toString() {
			return "DTSearch [value=" + value + ", regex=" + regex + "]";
		}

	}

	@Override
	public String toString() {
		return "DataTableRequest [draw=" + draw + ", length=" + length + ", order=" + order + ", search=" + search
				+ "]";
	}
	
}
