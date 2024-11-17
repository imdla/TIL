import { createBrowserRouter, Navigate } from "react-router-dom";
import Home from "../pages/Home";
import PostList from "../pages/PostList";
import RootLayout from "../RootLayout";
import PostDetail from "../pages/PostDetail";
import PostCreate from "../pages/postCreate";
import NotFound from "../pages/NotFound";
import LequestLogin from "../pages/LequestLogin";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    // errorElement: <NotFound />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "/posts",
        element: <PostList />,
      },
      {
        path: "/posts/create",
        element: <PostCreate />,
      },
      {
        path: "/posts/:postId",
        element: <PostDetail />,
        // loader: ({ params }) => {
        //   // store를 쓰는 흐름에서 (실제로는 X)
        //   // params에 해당하는 postsid가 없으면
        //   // notfound로 이동해줘

        //   // postid에 해당하는 api 요청
        //   // 정상 : 데이터를 준다
        //   // 비정상이다 : 리다이렉트한다

        //   // PostDetail 페이지에서 지정할 수도 있음
        //   return getPostId(params.postId);
        // },
      },
    ],
  },
  {
    path: "/notfound",
    element: <NotFound />,
  },
  {
    path: "/request-login",
    element: <LequestLogin />,
  },
  {
    path: "*",
    element: <Navigate to="/notfound" replace />,
  },
  // {
  //   path: '/music',
  //   element: <musiclayout></musiclayout>
  // }
]);

export default router;
