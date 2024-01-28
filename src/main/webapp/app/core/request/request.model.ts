export interface Pagination {
  page: number;
  size: number;
  sort: string[];
}

export interface Search {
  query: string;
  searchType: string;
  selectedColumn: string;
}

export interface SearchWithPagination extends Search, Pagination {}
