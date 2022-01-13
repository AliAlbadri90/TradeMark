import dayjs from 'dayjs/esm';
import { IDecreeType } from 'app/entities/decree-type/decree-type.model';
import { IDecreeCategory } from 'app/entities/decree-category/decree-category.model';
import { IMinister } from 'app/entities/minister/minister.model';
import { IGovernment } from 'app/entities/government/government.model';
import { DecreeStatus } from 'app/entities/enumerations/decree-status.model';

export interface IDecree {
  id?: number;
  documentNo?: string | null;
  decreeNo?: string | null;
  title?: string | null;
  details?: string | null;
  keywords?: string | null;
  pages?: number | null;
  decreeDate?: dayjs.Dayjs | null;
  year?: number | null;
  notes?: string | null;
  pdfFileContentType?: string | null;
  pdfFile?: string | null;
  pdfFileUrl?: string | null;
  wordFileContentType?: string | null;
  wordFile?: string | null;
  wordFileUrl?: string | null;
  decreeStatus?: DecreeStatus | null;
  decreeType?: IDecreeType | null;
  decreeCategory?: IDecreeCategory | null;
  minister?: IMinister | null;
  government?: IGovernment | null;
}

export class Decree implements IDecree {
  constructor(
    public id?: number,
    public documentNo?: string | null,
    public decreeNo?: string | null,
    public title?: string | null,
    public details?: string | null,
    public keywords?: string | null,
    public pages?: number | null,
    public decreeDate?: dayjs.Dayjs | null,
    public year?: number | null,
    public notes?: string | null,
    public pdfFileContentType?: string | null,
    public pdfFile?: string | null,
    public pdfFileUrl?: string | null,
    public wordFileContentType?: string | null,
    public wordFile?: string | null,
    public wordFileUrl?: string | null,
    public decreeStatus?: DecreeStatus | null,
    public decreeType?: IDecreeType | null,
    public decreeCategory?: IDecreeCategory | null,
    public minister?: IMinister | null,
    public government?: IGovernment | null
  ) {}
}

export function getDecreeIdentifier(decree: IDecree): number | undefined {
  return decree.id;
}
