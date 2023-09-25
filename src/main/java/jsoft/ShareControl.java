package jsoft;

public interface ShareControl {
	// chia sẻ bộ quản lý kết nối giữ các thể hiện của basic
		public ConnectionPool getCP();
		
		//yêu cầu các đối tượng trả lại kết nối sau khi lấy dữ liệu
		public void releaseConnection();
}
