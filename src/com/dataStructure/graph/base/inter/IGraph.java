package com.dataStructure.graph.base.inter;

import java.util.List;

public interface IGraph {
	void addEdge(int vStart,int vEnd);   //���һ����
	boolean hasEdge(int vStart,int vEnd);//�������֮���Ƿ��б�
	List<Integer> adjacentEdge(int vertex);//��ö����ڽӵĶ���
	int getVertex();						//������
	int getEdge();							//����
	void matrix();							//��ӡ������Ϣ
	boolean isDirected();					//�Ƿ�����
}
