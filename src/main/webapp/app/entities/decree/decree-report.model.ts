import dayjs from 'dayjs/esm';
import { IDecreeType } from 'app/entities/decree-type/decree-type.model';
import { IDecreeCategory } from 'app/entities/decree-category/decree-category.model';
import { IMinister } from 'app/entities/minister/minister.model';
import { IGovernment } from 'app/entities/government/government.model';
import { DecreeStatus } from 'app/entities/enumerations/decree-status.model';

export interface IDecreeReport {
  firstDecree?: number | null;
  lastDecree?: number | null;
  totalCount?: number | null;
  missingCount?: number | null;
  year?: number | null;
  noFileCount?: number | null;
  duplicate?: number | null;
  missingNumbers?: string | null;
  noFileNumbers?: string | null;
  ministerName?: string | null;
  governmentName?: string | null;
}

export class DecreeReport implements IDecreeReport {
  constructor(
    public firstDecree?: number | null,
    public lastDecree?: number | null,
    public totalCount?: number | null,
    public missingCount?: number | null,
    public year?: number | null,
    public noFileCount?: number | null,
    public duplicate?: number | null,
    public missingNumbers?: string | null,
    public noFileNumbers?: string | null,
    public ministerName?: string | null,
    public governmentName?: string | null
  ) {}
}
