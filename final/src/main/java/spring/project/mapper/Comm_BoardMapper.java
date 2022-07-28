package spring.project.mapper;

import java.util.List;

import spring.project.model.Comm_BoardDTO;

public interface Comm_BoardMapper {

	public int addComm_Board(Comm_BoardDTO dto);

	public List<Comm_BoardDTO> comm_BoardLists(int pnum);
	
	public Comm_BoardDTO getComm_Board(int Comm_num);

	public int maxComm_group();
	
	public int comm_BoardCount(int pnum);
		
	public int modComm_Board(Comm_BoardDTO dto);
	
	public int delComm_Board(int comm_num);
	
}
