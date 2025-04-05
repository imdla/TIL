import { Suspense } from "react";
import MovieInfo from "../../../../components/movie-info";
import MovieVideos from "../../../../components/movie-videos";
import { getMovie } from "../../../../components/movie-info";

interface IParams {
  params: { id: string };
}

export async function generateMetadata({ params }) {
  const movie = await getMovie(params.id);
  return {
    title: movie.title,
  };
}

export default async function MovieDetailPage({ params }) {
  return (
    <div>
      <Suspense fallback={<h1>Loading movie info</h1>}>
        <MovieInfo id={params.id} />
      </Suspense>
      <Suspense fallback={<h1>Loading movie videos</h1>}>
        <MovieVideos id={params.id} />
      </Suspense>
    </div>
  );
}
