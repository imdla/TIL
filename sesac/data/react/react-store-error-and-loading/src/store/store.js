import { configureStore } from "@reduxjs/toolkit";
import postsReducer from "./slices/postsSlice";
import loginReducer from "./slices/loginSlice";

const store = configureStore({
  reducer: {
    posts: postsReducer,
    isLogin: loginReducer,
  },
});

export default store;
