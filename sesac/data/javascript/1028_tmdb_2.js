import config from '../../apikey';
// 현재 상영 중인 영화 중 평점이 가장 높은 영화의 수익

const baseURL = 'https://api.themoviedb.org/3/movie';
const params = new URLSearchParams({
  api_key: config.TMDB_API_KEY,
});

// 현재 상영 중인 영화 리스트
async function getMovies() {
  const path = '/now_playing';

  const URL = `${baseURL}${path}?${params}`;

  const response = await fetch(URL);
  const data = await response.json();

  const movies = data.results;
  return movies;
}

// 가장 평점 높은 영화
function maxPopularMovie(movies) {
  let maxVoteAverage = 0;
  let maxVoteMovie = null;

  for (let movie of movies) {
    let curVoteAverage = movie.vote_average;
    if (maxVoteAverage < curVoteAverage) {
      maxVoteAverage = curVoteAverage;
      maxVoteMovie = movie;
    }
  }

  return maxVoteMovie;
}

// 가장 평점 높은 영화의 데이터
async function maxMovieRevenu(movieId) {
  const path = `/${movieId}`;

  const URL = `${baseURL}${path}?${params}`;
  const response = await fetch(URL);
  const data = await response.json();

  return data;
}

async function main() {
  const movies = await getMovies();
  const popularMovie = maxPopularMovie(movies);

  let movieId = popularMovie.id;
  const movieRevenu = await maxMovieRevenu(movieId);

  // 가장 평점 높은 영화의 데이터의 제목과 수익
  console.log(movieRevenu.revenue);
  console.log(movieRevenu.title);
}

main();
