import config from '../../apikey';
// 현재 상영 중인 영화 중 평점이 7 이상인 영화

// 현재 상영 중인 영화 리스트
async function getMovies() {
  const baseURL = 'https://api.themoviedb.org/3/movie';
  const path = '/now_playing';
  const params = new URLSearchParams({
    api_key: config.TMDB_API_KEY,
  });

  const URL = `${baseURL}${path}?${params}`;
  const response = await fetch(URL);
  const data = await response.json();
  const movies = data.results;

  return movies;
}

// 평점 7 이상인 영화
function maxOver7Movie(movies) {
  let over7Movies = [];

  for (let movie of movies) {
    let curVoteAverage = movie.vote_average;
    if (curVoteAverage >= 7) {
      over7Movies.push(movie.title);
    }
  }

  return over7Movies;
}

// 평점 7 이상인 영화 - filter
function maxOver7Movie2(movies) {
  let maxOver7Movie = movies.filter((movie) => movie.vote_average >= 7);

  return maxOver7Movie;
}

async function main() {
  const movies = await getMovies();
  const popularMovie = maxOver7Movie2(movies);

  // 가장 평점 높은 영화의 제목과 평점
  console.log(popularMovie);
}

main();
