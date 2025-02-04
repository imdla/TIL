import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
  name: "isLogin",
  initialState: false,
  reducers: {
    switchLogin: (state) => {
      return !state;
    },
  },
});

export const { switchLogin } = loginSlice.actions;
export default loginSlice.reducer;
