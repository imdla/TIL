import { createBrowserRouter } from "react-router-dom";
import MainLayout from "../layout/MainLayout";

import WebtoonLayout from "../layout/WebtoonLayout";
import WebtoonMain from "../pages/WebtoonMain";
import WebtoonDays from "../pages/WebtoonDays";
import WebtoonDayDetail from "../pages/WebtoonDayDetail";
import WebtoonDayDetailContent from "../pages/WebtoonDayDetailContent";

import NovelLayout from "../layout/NovelLayout";
import NovelMain from "../pages/NovelMain";
import NovelGenre from "../pages/NovelGenre";
import NovelGenreDetail from "../pages/NovelGenreDetail";
import NovelGenreDetailContent from "../pages/NovelGenreDetailContent";

const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    children: [
      {
        path: "/webtoon",
        element: <WebtoonLayout />,
        children: [
          {
            index: true,
            element: <WebtoonMain />,
          },
          {
            path: "/webtoon/days",
            element: <WebtoonDays />,
          },
          {
            path: "/webtoon/days/:dayId",
            element: <WebtoonDayDetail />,
            children: [
              {
                index: true,
                element: <WebtoonDayDetailContent />,
              },
            ],
          },
          ,
        ],
      },
      {
        path: "/novel",
        element: <NovelLayout />,
        children: [
          {
            index: true,
            element: <NovelMain />,
          },
          {
            path: "/novel/genre",
            element: <NovelGenre />,
          },
          {
            path: "/novel/genre/:genreId",
            element: <NovelGenreDetail />,
            children: [
              {
                index: true,
                element: <NovelGenreDetailContent />,
              },
            ],
          },
        ],
      },
    ],
  },
]);

export default router;
