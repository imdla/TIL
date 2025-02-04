import { createSlice } from "@reduxjs/toolkit";

const initialState = [
  {
    id: 1,
    title: "첫 번째 프로젝트",
    content:
      "간단한 Todo 앱을 만들었습니다. UI 구성과 상태 관리의 중요성을 배웠습니다.",
  },
  {
    id: 2,
    title: "리액트와 함께한 성장",
    content:
      "리액트를 이용해 컴포넌트 기반 개발을 익히고 재사용성을 높이는 방법을 배웠습니다.",
  },
  {
    id: 3,
    title: "팀 프로젝트 경험",
    content:
      "팀 프로젝트에서 협업하여 일정 관리 앱을 개발했고, Git을 활용한 버전 관리와 코드 리뷰의 중요성을 배웠습니다.",
  },
];

const postsSlice = createSlice({
  name: "posts",
  initialState,
  reducers: {
    addPost: (state, action) => {
      // 리스트에.push(새로운 데이터)
      state.push(action.payload);
    },
  },
});

export const { addPost } = postsSlice.actions;
export default postsSlice.reducer;
